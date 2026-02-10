
∂^
W

TipUITestscom.example.tiptimecalculate_20_percent_tip2¯≤ÇÃÄ≥Ì–:¯≤ÇÃ¿Ä©è◊W
‘+java.lang.RuntimeException: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.Espresso.onIdle(Espresso.java:357)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:378)
at androidx.compose.ui.test.EspressoLink_androidKt.runEspressoOnIdle(EspressoLink.android.kt:88)
at androidx.compose.ui.test.EspressoLink.runUntilIdle(EspressoLink.android.kt:82)
at androidx.compose.ui.test.AndroidComposeUiTestEnvironment.runTest(ComposeUiTest.android.kt:363)
at androidx.compose.ui.test.junit4.AndroidComposeTestRule$apply$1.evaluate(AndroidComposeTestRule.android.kt:295)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2627)
Caused by: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.util.concurrent.FutureTask.report(FutureTask.java:122)
at java.util.concurrent.FutureTask.get(FutureTask.java:191)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:349)
... 32 more
Caused by: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:99)
at androidx.test.espresso.base.BaseLayerModule.provideEventInjector(BaseLayerModule.java:128)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.provideEventInjector(BaseLayerModule_ProvideEventInjectorFactory.java:36)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:28)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:10)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:47)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:52)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:11)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:47)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:34)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:12)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:47)
at androidx.test.espresso.DaggerBaseLayerComponent$BaseLayerComponentImpl.uiController(DaggerBaseLayerComponent.java:280)
at androidx.test.espresso.Espresso.lambda$onIdle$2(Espresso.java:340)
at androidx.test.espresso.Espresso$$ExternalSyntheticLambda1.call(D8$$SyntheticClass:0)
at java.util.concurrent.FutureTask.run(FutureTask.java:317)
at android.os.Handler.handleCallback(Handler.java:1070)
at android.os.Handler.dispatchMessage(Handler.java:125)
at android.os.Looper.dispatchMessage(Looper.java:333)
at android.os.Looper.loopOnce(Looper.java:263)
at android.os.Looper.loop(Looper.java:367)
at android.app.ActivityThread.main(ActivityThread.java:9287)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:566)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:929)
Caused by: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.lang.Class.getMethod(Class.java:2934)
at java.lang.Class.getDeclaredMethod(Class.java:2913)
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:60)
... 24 more
'java.util.concurrent.ExecutionException‘+java.lang.RuntimeException: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.Espresso.onIdle(Espresso.java:357)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:378)
at androidx.compose.ui.test.EspressoLink_androidKt.runEspressoOnIdle(EspressoLink.android.kt:88)
at androidx.compose.ui.test.EspressoLink.runUntilIdle(EspressoLink.android.kt:82)
at androidx.compose.ui.test.AndroidComposeUiTestEnvironment.runTest(ComposeUiTest.android.kt:363)
at androidx.compose.ui.test.junit4.AndroidComposeTestRule$apply$1.evaluate(AndroidComposeTestRule.android.kt:295)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:68)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:59)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:463)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2627)
Caused by: java.util.concurrent.ExecutionException: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.util.concurrent.FutureTask.report(FutureTask.java:122)
at java.util.concurrent.FutureTask.get(FutureTask.java:191)
at androidx.test.espresso.Espresso.onIdle(Espresso.java:349)
... 32 more
Caused by: java.lang.RuntimeException: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:99)
at androidx.test.espresso.base.BaseLayerModule.provideEventInjector(BaseLayerModule.java:128)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.provideEventInjector(BaseLayerModule_ProvideEventInjectorFactory.java:36)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:28)
at androidx.test.espresso.base.BaseLayerModule_ProvideEventInjectorFactory.get(BaseLayerModule_ProvideEventInjectorFactory.java:10)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:47)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:52)
at androidx.test.espresso.base.UiControllerImpl_Factory.get(UiControllerImpl_Factory.java:11)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:47)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:34)
at androidx.test.espresso.base.UiControllerModule_ProvideUiControllerFactory.get(UiControllerModule_ProvideUiControllerFactory.java:12)
at androidx.test.espresso.core.internal.deps.dagger.internal.DoubleCheck.get(DoubleCheck.java:47)
at androidx.test.espresso.DaggerBaseLayerComponent$BaseLayerComponentImpl.uiController(DaggerBaseLayerComponent.java:280)
at androidx.test.espresso.Espresso.lambda$onIdle$2(Espresso.java:340)
at androidx.test.espresso.Espresso$$ExternalSyntheticLambda1.call(D8$$SyntheticClass:0)
at java.util.concurrent.FutureTask.run(FutureTask.java:317)
at android.os.Handler.handleCallback(Handler.java:1070)
at android.os.Handler.dispatchMessage(Handler.java:125)
at android.os.Looper.dispatchMessage(Looper.java:333)
at android.os.Looper.loopOnce(Looper.java:263)
at android.os.Looper.loop(Looper.java:367)
at android.app.ActivityThread.main(ActivityThread.java:9287)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:566)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:929)
Caused by: java.lang.NoSuchMethodException: android.hardware.input.InputManager.getInstance []
at java.lang.Class.getMethod(Class.java:2934)
at java.lang.Class.getDeclaredMethod(Class.java:2913)
at androidx.test.espresso.base.InputManagerEventInjectionStrategy.initialize(InputManagerEventInjectionStrategy.java:60)
... 24 more
"‡

logcatandroid 
«C:\Users\USER\AndroidStudioProjects\Calculator\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\logcat-com.example.tiptime.TipUITests-calculate_20_percent_tip.txt"±

device-infoandroidñ
ìC:\Users\USER\AndroidStudioProjects\Calculator\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\device-info.pb"≤

device-info.meminfoandroidè
åC:\Users\USER\AndroidStudioProjects\Calculator\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\meminfo"≤

device-info.cpuinfoandroidè
åC:\Users\USER\AndroidStudioProjects\Calculator\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\cpuinfo" *ñ
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriver†
ùC:\Users\USER\AndroidStudioProjects\Calculator\app\build\outputs\androidTest-results\connected\debug\Medium_Phone_API_36.1(AVD) - 16\testlog\test-results.log 2
text/plain