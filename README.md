## Ballerine Integration example

### Integration into Android version of KMP project

1. Generate JWT token in your backend which is required to access the Ballerine KYC flow APIs. Here is the link to the documentation on how to generate token.
2. Add gradle dependency for Ballerine webview in your app-level `build.gradle` file
```kt
dependencies {
   implementation("com.github.gau4sar:Ballerine-android-webview:1.0.5")
}
```
   We need to add the maven dependency for jitpack in settings.gradle
```kt
allprojects {
   repositories {
      ... 
      maven("https://jitpack.io")
   }
}
```
3. Add `BallerineKYCFlowWebview` composable to your Activity/Fragment to initiate the web KYC verification flow process.
   Then we receive the result of the callback function `onVerificationComplete` in your Activity/Fragment.
#### MainActivity.kt
```kt
BallerineKYCFlowWebView(
      outputFileDirectory = outputFileDirectory,
      cameraExecutorService = cameraExecutorService,
      url = "$BALLERINE_WEB_URL?/b_t=$BALLERINE_API_TOKEN",
      onVerificationComplete = { verificationResult ->
            
            //Do something with the verification result        
            
            // Here we are just displaying the verification result as a Toast message
            val toastMessage = "Idv result : ${verificationResult.idvResult} \n" +
                                    "Status : ${verificationResult.status} \n" +
                                    "Code : ${verificationResult.code}"

            // Here we are just displaying the verification result as Text on the screen
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show() 
    })
```
4. Once you have received the `VerificationResult` we can do further checks on the different values of the `VerificationResult` like `status`|`idvResult`|`code`|`isSync`.
   (As shown above in Point 3)