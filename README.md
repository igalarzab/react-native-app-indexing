# react-native-app-indexing

Integrates [Google App Indexing](https://developers.google.com/app-indexing) with React Native.


## Installation

```
$ npm install --save react-native-app-indexing
```

After installing the npm package you need to link the native modules. You can do so using [rnpm](https://github.com/rnpm/rnpm).

`$ rnpm link react-native-app-indexing`

After that, you will need to recompile your project with `react-native run-android` or `react-native run-ios`.


## Usage

```js
import AppIndexing from 'react-native-app-indexing'
```
