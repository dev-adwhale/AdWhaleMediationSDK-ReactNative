// 프로젝트/AdWhaleMediationAdView.js
import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent } from 'react-native';
const RNAdWhaleMediationAdViewKt = requireNativeComponent("RNAdWhaleMediationAdViewKt");

class AdWhaleMediationAdViewKt extends Component {
    
    render() {
        return (
            <RNAdWhaleMediationAdViewKt
                {...this.props}
            />
        );
    }
}

AdWhaleMediationAdViewKt.propTypes = {

}

module.exports = AdWhaleMediationAdViewKt;