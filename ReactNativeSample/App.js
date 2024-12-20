/**
 * ADwhale Mediation SDK Sample
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */
// 프로젝트/App.js

// 모듈 import
import React, { Component } from 'react';
import {SafeAreaView, View, ScrollView, StyleSheet, NativeModules, NativeEventEmitter } from 'react-native';
import BannerAd from './src/java/BannerAd';
import InterstitialAd from './src/java/InterstitialAd';
import RewardAd from './src/java/RewardAd';

// 상수 정의
const { RNAdWhaleMediationAdSettingModule } = NativeModules;

// 스타일 정의
const styles = StyleSheet.create({
    safeArea: {
        flex: 1,
        backgroundColor: 'white',
    },
    mainViewStyle: {
        flex: 1,
        marginTop: 50
    },
    contentViewStyle: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center'                
    },
});

// App 클래스 정의(React.Component 확장)
class App extends Component {

    componentDidMount() {
        this._initialize();
    }

    // 화면 렌더링
    render() {
        
        return (            
            <SafeAreaView style={styles.safeArea}>
                <View style={styles.mainViewStyle}>
                    <ScrollView>
                        <View style={styles.contentViewStyle}>
                            <BannerAd />
                            <InterstitialAd />
                            <RewardAd />
                        </View>
                    </ScrollView>
                </View>
            </SafeAreaView>
        );
    }

    // SDK 초기화
    _initialize = () => {
        RNAdWhaleMediationAdSettingModule.initialize()
            .then((statusCode) => {
                if (statusCode === 100) {
                    console.log('AdWhale SDK initialized successfully');
                } else {
                    console.error('AdWhale SDK initialization failed');
                }
            })
            .catch((error) => {
                console.error('Error initializing AdWhale SDK:', error);
            });
    }
};

export default App;
