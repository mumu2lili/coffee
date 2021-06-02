package com.piggy.coffee.k8s.util;


import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetrics;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public final class K8sUtils {

    public static String buildContainerName(String imageName) {
        StringBuilder sb = new StringBuilder();
        char[] chars = imageName.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-') {

            } else {
                c = '-';
            }
            sb.append(c);
        }

        return  sb.toString();

    }

    public static String buildPodName(boolean canParallel, boolean isMaster, long id, int slaveIndex) {
        if (canParallel) {
            if (isMaster) {
                return id + "-master";
            } else {
                return id + "-slave" + slaveIndex;
            }
        } else {
            return id + "-run";
        }
    }


    public static boolean isErrImagePull(Pod pod) {
        List<ContainerStatus> list = pod.getStatus().getContainerStatuses();
        for (ContainerStatus cs : list) {
            ContainerState state = cs.getState();
            if (state == null) {
                continue;
            }
            ContainerStateWaiting wating = state.getWaiting();
            if (wating == null) {
                continue;
            }
            boolean r = "ErrImagePull".equals(wating.getReason());
            if (r) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPodCompleted(Pod pod) {
        List<PodCondition> list = pod.getStatus().getConditions();
        for (PodCondition c : list) {
            if ("Ready".equals(c.getType())) {
                if ("False".equals(c.getStatus()) && "PodCompleted".equals(c.getReason())) {
                    return true;
                }
                break;
            }
        }

        return false;
    }

    public static boolean isPodRunning(Pod pod) {
        return "Running".equalsIgnoreCase(pod.getStatus().getPhase());
    }

    public static String getNodeIp(Node node) {
        List<NodeAddress> list = node.getStatus().getAddresses();
        for (NodeAddress nodeAddress : list) {
            if ("InternalIP".equals(nodeAddress.getType())) {
                return nodeAddress.getAddress();
            }
        }

        return null;
    }

    public static double getCapacityCpu(Node node) {
        Quantity quantity = node.getStatus().getCapacity().get("cpu");
        return calCpu(quantity);
    }

    public static int getCapacityMemory(Node node) {
        Quantity quantity = node.getStatus().getCapacity().get("memory");
        return calMemory(quantity);
    }

    public static double getAllocatableCpu(Node node) {
        Quantity quantity = node.getStatus().getAllocatable().get("cpu");
        return calCpu(quantity);
    }

    public static int getAllocatableMemory(Node node) {
        Quantity quantity = node.getStatus().getAllocatable().get("memory");
        return calMemory(quantity);
    }

    public static double getRequestCpu(Pod pod) {
        Quantity quantity = pod.getSpec().getContainers().get(0).getResources().getRequests().get("cpu");
        return calCpu(quantity);
    }

    public static Double calCpu(Quantity quantity) {
        String unit = quantity.getFormat();
        if (StringUtils.isBlank(unit)) {
            unit = "";
        }
        String cpu = quantity.getAmount();
        if (StringUtils.isBlank(cpu)) {
            return null;
        }
        // cpu可能 的形式 100m, "2", 1。新版没有这种情况
        cpu = cpu.trim().replaceAll("\"", "");
        BigDecimal bd;
        if (unit.equals("n")) {
            bd = new BigDecimal(cpu).divide(BigDecimal.valueOf(1000 * 1000 * 1000), 3, BigDecimal.ROUND_HALF_UP);
        } else if (unit.equals("u")) {
            bd = new BigDecimal(cpu).divide(BigDecimal.valueOf(1000 * 1000), 3, BigDecimal.ROUND_HALF_UP);
        } else if (unit.equals("m")) {
            bd = new BigDecimal(cpu).divide(BigDecimal.valueOf(1000), 3, BigDecimal.ROUND_HALF_UP);
        } else {
            bd = new BigDecimal(cpu);
        }

        return bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Integer calMemory(Quantity quantity) {
        String unit = quantity.getFormat();
        if (StringUtils.isBlank(unit)) {
            unit = "";
        }
        String memory = quantity.getAmount();
        if (StringUtils.isBlank(memory)) {
            return null;
        }

        BigDecimal bd;
        if (unit.equals("Ki")) {
            bd = new BigDecimal(memory).divide(BigDecimal.valueOf(1024), 0, BigDecimal.ROUND_HALF_UP);
        } else if (unit.equals("Mi")) {
            bd = new BigDecimal(memory);
        } else if (unit.equals("Gi")) {
            bd = new BigDecimal(memory).multiply(BigDecimal.valueOf(1024));
        } else if (unit.equals("K")) {
            bd = new BigDecimal(memory).multiply(BigDecimal.valueOf(1000)).divide(BigDecimal.valueOf(1024 * 1024), 0, BigDecimal.ROUND_HALF_UP);
        } else if (unit.equals("M")) {
            bd = new BigDecimal(memory).multiply(BigDecimal.valueOf(1000 * 1000)).divide(BigDecimal.valueOf(1024 * 1024), 0, BigDecimal.ROUND_HALF_UP);
        } else if (unit.equals("G")) {
            bd = new BigDecimal(memory).multiply(BigDecimal.valueOf(1000 * 1000 * 1000)).divide(BigDecimal.valueOf(1024 * 1024 * 1024), 0, BigDecimal.ROUND_HALF_UP);
        } else {
            bd = new BigDecimal(memory).divide(BigDecimal.valueOf(1024 * 1024), 0, BigDecimal.ROUND_HALF_UP);
        }

        Integer value = bd.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return value;
    }


    public static boolean getDiskPressure(Node node) {
        boolean r = true;
        List<NodeCondition> list = node.getStatus().getConditions();
        for (NodeCondition c : list) {
            if ("DiskPressure".equals(c.getType())) {
                r = Boolean.valueOf(c.getStatus());
                break;
            }
        }
        return r;

    }

    // metrics start ...
    public static Double getUsageCpu(NodeMetrics k8sNodeMetrics) {
        Quantity quantity = k8sNodeMetrics.getUsage().get("cpu");
        return K8sUtils.calCpu(quantity);

    }


    public static Integer getUsageMemory(NodeMetrics k8sNodeMetrics) {
        Quantity quantity = k8sNodeMetrics.getUsage().get("memory");
        return K8sUtils.calMemory(quantity);

    }
    // metrics end ...
}