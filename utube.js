import React from 'react';
import { requireNativeComponent, Platform, View } from 'react-native';

const YoutubePlayerView = Platform.select({
  ios: View,
  android: requireNativeComponent('YoutubePlayerView'),
});

export default class UTubePlayerView extends React.Component {
  render() {
    const { width = '100%', height = 200 } = this.props;
    return <YoutubePlayerView {...this.props} style={{ width, height }} />;
  }
}
