package io.ballerine.android_sdk_example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.ballerine.ballerine_android_sdk.BallerineKYCFlowWebView
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {

    companion object {

        const val BALLERINE_WEB_URL = "https://moneco.dev.ballerine.app"

        /**
         * BALLERINE_API_TOKEN needs to be generated from the backend. Please follow the below link for more information on how to generate the tole
         * https://www.notion.so/ballerine/Ballerine-s-Developers-Documentation-c9b93462384446ef98ffb69d16865981#228240bfef6f48f3971db07ef03368c3
         */
        const val BALLERINE_API_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbmRVc2VySWQiOiJhMzEyYzk1ZC03ODE4LTQyNDAtOTQ5YS1mMDRmNDEwMzRlYzEiLCJjbGllbnRJZCI6IjI2YTRmOTFiLWFhM2UtNGNlNS1hZDE1LWYzNTRiOTI1NmJmMCIsImlhdCI6MTY1OTYxNzM1NCwiZXhwIjoxNjkwMzc1NzU0LCJpc3MiOiIyNmE0ZjkxYi1hYTNlLTRjZTUtYWQxNS1mMzU0YjkyNTZiZjAifQ.Nm-j9jVh7ByHoo0WkqnIQeVR0mNWcV3TZUNknSLRtbc"
    }

    private lateinit var outputFileDirectory: File
    private lateinit var cameraExecutorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        outputFileDirectory = getOutputDirectory()
        cameraExecutorService = Executors.newSingleThreadExecutor()

        setContent {
            BallerineKYCFlowWebView(
                outputFileDirectory = outputFileDirectory,
                cameraExecutorService = cameraExecutorService,
                url = "$BALLERINE_WEB_URL?b_t=$BALLERINE_API_TOKEN",
                onVerificationComplete = { verificationResult ->

                    //TODO :: Use the verification result returned

                    val toastMessage = "Idv result : ${verificationResult.idvResult} \n" +
                            "Status : ${verificationResult.status} \n" +
                            "Code : ${verificationResult.code}"

                    // Here we are just displaying the verification result as Text on the screen
                    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                })
        }
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, "Document").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }
}