![Image Alt Text](screenshots/header.png)

# JetpackLoading

In almost all applications, there are processes that run asynchronously, such as API requests or long-running tasks. In these cases, developers usually use the loading view to show users that something is in progress.
JetpackLoading is a collection of nice loading animations in Jetpack Compose, adapted from the famous [AVLoadingIndicatorView](https://github.com/HarlonWang/AVLoadingIndicatorView) library.

See the animations in the [Demo](https://github.com/MahboubehSeyedpour/JetpackLoading#demo) section and their use in [Usage](https://github.com/MahboubehSeyedpour/JetpackLoading#usage)


## Demo
<img src="screenshots/screenshot.gif" alt="GIF 1" width="300" height="600">

## Usage

- #### Step 1
  Add it in your root build.gradle:
  
  ```bash
  allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
  
   
- #### Step 2
  Add the dependency
  
  ```bash
  dependencies {
	implementation 'com.github.MahboubehSeyedpour:jetpack-loading:0.1.0'
  }
  
   
- #### Step 3  
  It's very simple to use. Just add component where you want
  ```bash
   @Composable
   fun Greeting() {
       ...
       PacmanIndicator()
       ...
   }
  ```
  
  You can also control the details of the animations using parameters
  ```bash
   @Composable
   fun Greeting() {
       ...
       PacmanIndicator(color = Color.Black, ballDiameter = 60f, canvasSize = 60.dp, animationDuration = 1000)
       ...
   }
  ```

Enjoy JetpackLoading 😊
  
##  Indicators

The indicators are as follows:

Row 1
 - `PulsatingDot`
 - `GridPulsatingDot`
 - `CircularPulsatingIndicator`
 - `BallClipRotatePulseIndicator`

 Row 2
 - `SquareSpinIndicator`
 - `BallClipRotateMultipleIndicator`
 - `BallPulseRiseIndicator`
 - `BallRotateIndicator`

  Row 3
 - `CubeTransitionIndicator`
 - `BallZigZagIndicator`
 - `BallZigZagDeflectIndicator`
 - `BallTrianglePathIndicator`

Row 4
 - `BallScaleIndicator`
 - `LineScaleIndicator`
 - `LineScaleIndicator`
 - `BallScaleMultipleIndicator`

Row 5
 - `BallPulseSyncIndicator`
 - `BallBeatIndicator`
 - `LineScaleIndicator`
 - `LineScaleIndicator`

 Row 6
 - `BallScaleRippleIndicator`
 - `BallScaleRippleMultipleIndicator`
 - `BallSpinFadeLoaderIndicator`
 - `LineSpinFadeLoaderIndicator`

  Row 7
 - `TriangleSpinIndicator`
 - `PacmanIndicator`
 - `BallGridBeatIndicator`
 - `SemiCircleSpinIndicator`


## 🔗 Contact
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mahboubehseyedpour)

[![GMail](https://img.shields.io/badge/gmail-1DA1F2?style=for-the-badge&logo=gmail&logoColor=Red)](mailto:https://www.linkedin.com/in/mahboubehseyedpour)


## License

```
Copyright 2015 jack wang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
