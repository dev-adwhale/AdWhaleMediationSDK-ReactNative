// 프로젝트/AdWhaleMediationAdView.js
import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent } from 'react-native';
const RNAdWhaleMediationAdView = requireNativeComponent("RNAdWhaleMediationAdView");

class AdWhaleMediationAdView extends Component {
    
    render() {
        return (
            <RNAdWhaleMediationAdView
                {...this.props}
            />
        );
    }
}

AdWhaleMediationAdView.propTypes = {

}

module.exports = AdWhaleMediationAdView;