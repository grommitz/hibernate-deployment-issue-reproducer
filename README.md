# Hibernate 6 Deployment Error

This simple app deploys fine when using Hibernate 6.1 (see master branch). But when I upgrade it to Hibernate 6.2 (hibernate62 branch), it fails to deploy with this error:

<pre>
[2023-03-13T18:32:24.639+0000] [] [SEVERE] [NCLS-CORE-00026] [javax.enterprise.system.core] [tid: _ThreadID=1 _ThreadName=main] [timeMillis: 1678732344639] [levelValue: 1000] [[
Exception during lifecycle processing
java.lang.ClassCircularityError: org/hibernate/bytecode/enhance/spi/EnhancementContextWrapper
at org.hibernate.jpa.internal.enhance.EnhancingClassTransformerImpl.transform(EnhancingClassTransformerImpl.java:50)
at org.glassfish.persistence.jpa.ServerProviderContainerContractInfo$1.transform(ServerProviderContainerContractInfo.java:101)
at org.glassfish.web.loader.WebappClassLoader$4.preprocess(WebappClassLoader.java:3395)
at org.glassfish.web.loader.WebappClassLoader.defineLoadedClass(WebappClassLoader.java:3487)
at org.glassfish.web.loader.WebappClassLoader.findClass(WebappClassLoader.java:1234)
at org.glassfish.web.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1806)
at org.glassfish.web.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1684)
at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:162)
at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1380)
at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1451)
at org.hibernate.jpa.HibernatePersistenceProvider.createContainerEntityManagerFactory(HibernatePersistenceProvider.java:142)
at org.glassfish.persistence.jpa.PersistenceUnitLoader.loadPU(PersistenceUnitLoader.java:207)
at org.glassfish.persistence.jpa.PersistenceUnitLoader.<init>(PersistenceUnitLoader.java:114)
at org.glassfish.persistence.jpa.JPADeployer$1.visitPUD(JPADeployer.java:267)
at org.glassfish.persistence.jpa.JPADeployer$PersistenceUnitDescriptorIterator.iteratePUDs(JPADeployer.java:571)
at org.glassfish.persistence.jpa.JPADeployer.createEMFs(JPADeployer.java:286)
at org.glassfish.persistence.jpa.JPADeployer.prepare(JPADeployer.java:183)
at com.sun.enterprise.v3.server.ApplicationLifecycle.prepareModule(ApplicationLifecycle.java:1197)
at com.sun.enterprise.v3.server.ApplicationLifecycle.prepare(ApplicationLifecycle.java:511)
at org.glassfish.deployment.admin.DeployCommand.execute(DeployCommand.java:613)
at com.sun.enterprise.v3.admin.CommandRunnerImpl$2$1.run(CommandRunnerImpl.java:556)
at com.sun.enterprise.v3.admin.CommandRunnerImpl$2$1.run(CommandRunnerImpl.java:552)
at java.base/java.security.AccessController.doPrivileged(Native Method)
at java.base/javax.security.auth.Subject.doAs(Subject.java:361)
at com.sun.enterprise.v3.admin.CommandRunnerImpl$2.execute(CommandRunnerImpl.java:551)
at com.sun.enterprise.v3.admin.CommandRunnerImpl$3.run(CommandRunnerImpl.java:582)
at com.sun.enterprise.v3.admin.CommandRunnerImpl$3.run(CommandRunnerImpl.java:574)
at java.base/java.security.AccessController.doPrivileged(Native Method)
at java.base/javax.security.auth.Subject.doAs(Subject.java:361)
at com.sun.enterprise.v3.admin.CommandRunnerImpl.doCommand(CommandRunnerImpl.java:573)
at com.sun.enterprise.v3.admin.CommandRunnerImpl.doCommand(CommandRunnerImpl.java:1497)
at com.sun.enterprise.v3.admin.CommandRunnerImpl$ExecutionContext.execute(CommandRunnerImpl.java:1879)
at com.sun.enterprise.v3.admin.CommandRunnerImpl$ExecutionContext.execute(CommandRunnerImpl.java:1755)
at com.sun.enterprise.admin.cli.embeddable.DeployerImpl.deploy(DeployerImpl.java:131)
at com.sun.enterprise.admin.cli.embeddable.DeployerImpl.deploy(DeployerImpl.java:104)
at fish.payara.micro.impl.PayaraMicroImpl.deployAll(PayaraMicroImpl.java:1739)
at fish.payara.micro.impl.PayaraMicroImpl.bootStrap(PayaraMicroImpl.java:1092)
at fish.payara.micro.impl.PayaraMicroImpl.create(PayaraMicroImpl.java:236)
at fish.payara.micro.impl.PayaraMicroImpl.main(PayaraMicroImpl.java:223)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.base/java.lang.reflect.Method.invoke(Method.java:566)
at fish.payara.micro.boot.loader.MainMethodRunner.run(MainMethodRunner.java:50)
at fish.payara.micro.boot.loader.Launcher.launch(Launcher.java:114)
at fish.payara.micro.boot.loader.Launcher.launch(Launcher.java:73)
at fish.payara.micro.boot.PayaraMicroLauncher.create(PayaraMicroLauncher.java:88)
at fish.payara.micro.boot.PayaraMicroLauncher.main(PayaraMicroLauncher.java:72)
at fish.payara.micro.PayaraMicro.main(PayaraMicro.java:467)
]]
</pre>

## Usage:

From a linux shell:

`./run.sh`

This does the build (with Maven) and downloads Payara Micro for doing the deployment.