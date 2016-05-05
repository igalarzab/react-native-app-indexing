'use strict'

import url from 'url'
import {Linking, NativeModules} from 'react-native'


export default class AppIndexing {
  static getInitialURL() {
    return Linking.getInitialURL().then((urltext) => {
      if (!urltext) {
        return null;
      }
      
      let parser = url.parse(urltext, true, true)

      return {
        protocol: parser.protocol,
        hostname: parser.hostname,
        port: parser.port,
        pathname: parser.pathname,
        query: parser.query,
        hash: parser.hash,
      }
    })
  }

  static startViewAction(title, url, description=null) {
    return NativeModules.AppIndexing.startViewAction(title, description, url)
  }

  static stopViewAction() {
    return NativeModules.AppIndexing.stopViewAction()
  }
}