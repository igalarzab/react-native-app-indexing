# react-native-app-indexing

[![NPM version][npm-image]][npm-url]


Integrates [Google App Indexing](https://developers.google.com/app-indexing) with React Native.

This package is **not** production ready.


## Installation

```
$ npm install --save react-native-app-indexing
```

After installing the npm package you need to link the native modules. You can do so
using [rnpm](https://github.com/rnpm/rnpm).

```
$ rnpm link react-native-app-indexing
```

Then you will need to follow some extra steps.

### Android

Add in the main activity of your manifest (`android/app/src/mainAndroidManifest.xml`) the following `intent-filter`.

```xml
<activity
    ...>
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="http" />
        <data android:scheme="https" />
        <data android:host="www.YOUR-DOMAIN.com" />
    <intent-filter>
</activity>
```

You can obtain more info about how to add the `intent-filter` and it options in
[the following link](https://developers.google.com/app-indexing/android/app#add-intent-filters-for-http-urls).

### iOS

iOS is not yet supported.



## Usage


### Deep links

```js
import AppIndexing from 'react-native-app-indexing'
```

### Add App Indexing API

```js
import AppIndexing from 'react-native-app-indexing'
```


## License

This project is available under the MIT license. See the [LICENSE](LICENSE) file for more info.


[npm-image]: https://img.shields.io/npm/v/react-native-app-indexing.svg?style=flat
[npm-url]: https://npmjs.org/package/react-native-app-indexing
