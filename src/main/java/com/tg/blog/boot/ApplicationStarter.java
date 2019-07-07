package com.tg.blog.boot;


import com.tg.blog.base.enums.Env;


public abstract class ApplicationStarter {
    //环境前缀
    private static final String  ENV_PRX = "--spring.profiles.active=";
    //环境属性名
    private static final String  ENV_PROPERTY_NAME = "user.env";

   /**
   * @Description: 系统启动环境参数获取
   * @Author: TengGang
   * @Date: 2019/7/6 13:35
   * @Param: [args]
   * @return: void
   */
    public static void start(String[] args){
        String env = Env.LOCAL.name();
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (null != arg && arg.startsWith(ENV_PRX)) {
                    env = arg.substring(ENV_PRX.length());
                }
            }
        }

        System.out.println(" *********************************");
        System.out.println("*** 				***");
        System.out.println("*** server is starting,env is	***");
        System.out.println("*** 				***");
        System.out.println("***		" + env.toUpperCase() + " !		***");
        System.out.println("*** 				***");
        System.out.println(" *********************************");
        System.setProperty(ENV_PROPERTY_NAME, env.toLowerCase());
    }
}
