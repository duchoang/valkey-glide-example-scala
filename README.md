## Run
Ensure that you have a server running on "localhost" on port "6379". To run the ClusterExample, make sure that the server has cluster mode enabled. If the server is running on a different host and/or port, update the StandaloneExample or ClusterExample with a configuration that matches your server settings.

To run the Standalone example:
```shell
cd valkey-glide/examples/scala
sbt "runMain StandaloneExample"
```

To run the Cluster example:
```shell
cd valkey-glide/examples/scala
sbt "runMain ClusterExample"
```

## Version
These examples are running `valkey-glide` version `1.+`. In order to change the version, update the following section in the `build.sbt` file:
```scala
libraryDependencies += "io.valkey" % "valkey-glide" % "1.+" classifier platformClassifier
```

## Issue with docker on Mac
If we compile and build to docker image on Mac, we would get runtime error as follows:
```
sbt:example> docker:publishLocal
$> docker run --rm -it --entrypoint bash example:latest
bash-5.2$ ./bin/standalone-example
```

Runtime error:
```
Exception in thread "main" java.lang.UnsatisfiedLinkError: /tmp/nativeutils1033208101620425/libglide_rs.so: /tmp/nativeutils1033208101620425/libglide_rs.so: cannot open shared object file: No such file or directory (Possible cause: can't load AMD 64-bit .so on a AARCH64-bit platform)
  at java.base/java.lang.ClassLoader$NativeLibrary.load0(Native Method)
  at java.base/java.lang.ClassLoader$NativeLibrary.load(ClassLoader.java:2450)
  at java.base/java.lang.ClassLoader$NativeLibrary.loadLibrary(ClassLoader.java:2506)
  at java.base/java.lang.ClassLoader.loadLibrary0(ClassLoader.java:2705)
  at java.base/java.lang.ClassLoader.loadLibrary(ClassLoader.java:2635)
  at java.base/java.lang.Runtime.load0(Runtime.java:768)
  at java.base/java.lang.System.load(System.java:1854)
  at glide.ffi.resolvers.NativeUtils.loadLibraryFromJar(NativeUtils.java:105)
  at glide.ffi.resolvers.NativeUtils.loadGlideLib(NativeUtils.java:44)
  at glide.ffi.resolvers.LoggerResolver.<clinit>(LoggerResolver.java:7)
  at glide.api.logging.Logger.initLogger(Logger.java:68)
  at glide.api.logging.Logger.setLoggerConfig(Logger.java:230)
  at glide.api.logging.Logger.setLoggerConfig(Logger.java:243)
  at StandaloneExample$.main(StandaloneExample.scala:119)
  at StandaloneExample.main(StandaloneExample.scala)
```
