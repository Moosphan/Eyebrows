# Eyebrows
[ ![Download](https://api.bintray.com/packages/moosphon/maven/Eyebrows/images/download.svg) ](https://bintray.com/moosphon/maven/Eyebrows/_latestVersion)

An sportive and pretty ui library for Android which brings you eyebrows feeling.

![image](https://github.com/Moosphan/Eyebrows/blob/master/Eyebrows/image/eyebrows.gif)

## How to use

- Gradle:

  > Project —>build.gradle:

  ```
  allprojects {
      repositories {
          maven { url "https://maven.google.com" }
          jcenter()
      }
  }
  ```
  > Moudle—>build.gradle:

  ```
  compile 'com.moos:Eyebrows:1.0.0'
  ```

  ​

- Maven:

  ```
  <dependency>
    <groupId>com.moos</groupId>
    <artifactId>Eyebrows</artifactId>
    <version>1.0.0</version>
    <type>pom</type>
  </dependency>
  ```

- Gradual color background:

  If you only want to use the gradual color background, you can use like this:

  ```
      private Eyebrows eyebrows;

      private void animateBackground(){
          eyebrows = new Eyebrows.Builder()
                  .bindTargetView(targetView)
                  .setDuration(5000)
                  .setGradientAnimation(Eyebrows.ANIM_RED_PURPLE)
                  .build();
      }
      
      @Override
      protected void onResume() {
          super.onResume();
          eyebrows.startGradientAnimation();
      }

      @Override
      protected void onPause() {
          super.onPause();
          eyebrows.stopGradientAnimation();
      }
  ```

- with sportive bubbles:

  If you also want to use the sportive and pretty bubbles to embellish the background, you can use `EyebrowsView` in xml like that:

  ```
  <?xml version="1.0" encoding="utf-8"?>
  <com.moos.library.view.EyebrowsView
      android:id="@+id/targetView"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:bubble_min_size="22"
      app:bubble_max_size="40"
      xmlns:android="http://schemas.android.com/apk/res/android"
      tools:context="com.moos.eyebrows.MainActivity"
      xmlns:tools="http://schemas.android.com/tools">
      
  </com.moos.library.view.EyebrowsView>

  ```

  In activity, you can use more flexible like this:

  ```
  private EyebrowsView targetView;
  private Eyebrows eyebrows;
  private List<Integer> colors = new ArrayList<>();
  private Vector<EyebrowsAnimatorMaker> animatorMakers = new Vector<>();


   @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          targetView = findViewById(R.id.targetView);
          animateBackground();
          animateBubbles();

      }

      private void animateBackground(){
          eyebrows = new Eyebrows.Builder()
                  .bindTargetView(targetView)
                  .setDuration(5000)
                  .setGradientAnimation(Eyebrows.ANIM_RED_PURPLE)
                  .build();
      }

      private void animateBubbles(){
          colors.add(getResources().getColor(R.color.trans_yellow));
          colors.add(getResources().getColor(R.color.trans_white));
          colors.add(getResources().getColor(R.color.trans_gray));
          targetView.setEyebrowsShapeColors(colors);
          targetView.setEyebrowsShapeSize(20, 50);
          animatorMakers.add(new EyebrowsTranslateAnimator(5000, 6000, EyebrowsTranslateAnimator.Direction.UP_TO_DOWN,  new AccelerateInterpolator()));
          animatorMakers.add(new EyebrowsScaleAnimator(2000, 3000, new AccelerateDecelerateInterpolator(), 20, 50));
          animatorMakers.add(new EyebrowsShakeAnimator(2000, 3000, EyebrowsShakeAnimator.ShakeDirection.HORIZONTAL, new AccelerateInterpolator()));
          targetView.setEyebrowsAnimators(animatorMakers);
      }

      @Override
      protected void onResume() {
          super.onResume();
          eyebrows.startGradientAnimation();
      }

      @Override
      protected void onPause() {
          super.onPause();
          eyebrows.stopGradientAnimation();
      }
  ```

  You can set animations for bubbles, I provided with three types of  animations like: `EyebrowsTranslateAnimator`, `EyebrowsScaleAnimator`, `EyebrowsShakeAnimator`.You can customize your own animator for your bubbles, just extend class `EyebrowsAnimatorMaker`.



## To-do

I will try to provide more shapes of trimmings and kinds of animations, i hope you can improve it with me by issues or pull requests.

## Thanks to 

[Spark](https://github.com/TonnyL/Spark)  

[Adrián Lomas](https://github.com/glomadrian)

## About me

E-mail:moosphon@gmail.com

Twitter:@Moos47405337

QQ:4674521



## License:

```
Copyright 2018 moosphon

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