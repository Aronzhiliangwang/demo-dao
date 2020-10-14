# 说明

1. dao模块【demo-dao】 [仓库地址](https://github.com/Aronzhiliangwang/demo-dao)
2. service模块【demo-service】 [仓库地址](https://github.com/Aronzhiliangwang/demo-service)
3. dao模块【demo-web】 [仓库地址](https://github.com/Aronzhiliangwang/demo-web)

以上三个项目是作为独立的Spring Boot项目创建的，既互相依赖，亦可独立开发，运行。其中`dao`和`service`项目是以junit测试运行


---------
parent模块【evan-parent】父级依赖[仓库地址](https://github.com/Aronzhiliangwang/evan-parent)

作为父级依赖管理项目，只有pom文件而已，类似`spring boot parent`;如果是公司开发，那么这个项目会compile到公司的maven私库中，你也可以clone到自己电脑`maven install`到个人电脑的macen库中，
如果你想运行dome，这也是必须的

> 另外注意，依赖关系：web->service->dao，如果想独立运行开发任何一个模块，必须将依赖的项目`install`到个人maven库中或公司私库中
 
 
# 多模块统一开发运行

需要重新创建一个maven项目，利用`git submodule` 为该项目把`dao`，`service`，`web`，分别添加到git子模块
> 注意，这里的子模块和maven多模块项目没半毛钱关系，只是为了当所有的模块存在于一个项目时，避免git 提交推送乱七八糟，原则上你改了哪个模块就应该推送到哪个模块

在`idea`设置，版本控制中添加每个模块的vcs映射

接下来就是设置主项目的模块设置了，把项目路径下的3个模块分别导入即可（git submodule直接将三个模块clone到主项目的根路径即可也最好），一切大吉

统一开发的项目演示demo[仓库](https://github.com/Aronzhiliangwang/demo-test)