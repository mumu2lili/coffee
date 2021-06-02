package com.piggy.coffee.k8s.util;


import com.piggy.coffee.k8s.domain.CoffeeCmd;
import com.piggy.coffee.k8s.domain.CoffeePod;
import com.piggy.coffee.k8s.domain.K8sClientConfigIf;

public final class K8sCmdUtils {

    public static String buildCmd(CoffeeCmd cCmd, CoffeePod cPod, K8sClientConfigIf k8sClientConfig) {
        String cmd = "timeout " + (cCmd.getTimeLimit() + 1) + " kubectl --kubeconfig="
                + k8sClientConfig.getKubeConfig() + " exec " + cPod.getName() + " -- timeout " + cCmd.getTimeLimit() + "  " + cCmd.getCmd();
        return cmd;

    }

}