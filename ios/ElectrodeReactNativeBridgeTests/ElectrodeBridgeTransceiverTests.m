//
//  ElectrodeBridgeTransceiverTests.m
//  ElectrodeReactNativeBridge
//
//  Created by Claire Weijie Li on 3/27/17.
//  Copyright © 2017 Walmart. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "ElectrodeBridgeBaseTests.h"

@interface ElectrodeBridgeTransceiverTests : ElectrodeBridgeBaseTests

@end

@implementation ElectrodeBridgeTransceiverTests

-(void)testSendTimeOutRequest
{
    [super initializeBundle];
    XCTestExpectation* expectation = [self createExpectationWithDescription:@"testSendTimeOutRequest"];
    
    id<ElectrodeNativeBridge> nativeBridge = [self.bridge moduleForClass:[ElectrodeBridgeTransceiver class]];
    ElectrodeBridgeRequestNew *request = [ElectrodeBridgeRequestNew createRequestWithName:@"test1"];
    MockElectrodeBridgeResponseListener *listener = [[MockElectrodeBridgeResponseListener alloc] initWithExpectation:expectation failureBlock:^(id failureMessage) {
        XCTAssertNil(failureMessage);
        [expectation fulfill];
    }];
    
    [nativeBridge sendRequest:request withResponseListener:listener];
    
    
    [self waitForExpectationToFullFillOrTimeOut];

}

-(void)testSendRequestWithEmptyRequestDataAndNonEmptyResponse
{
    
}

-(void)testSendRequestWithRequestDataAndEmptyResponse
{
    
}

-(void)testSendRequestWithRequestDataAndEmptyResponseWithJSRequestHandler
{
    
}

-(void)testEmitEventWithSimpleDataFromNative
{
    
}

-(void)testEmitEventWithSimpleDataFromJS
{
    
}

-(void)testEmitEventWithComplexDataFromNative
{
    
}

-(void)testEmitEventWithComplexDataFromJS
{
    
}

-(void)testGetEmptyArrayFromJsToNative
{
    
}

-(void)testGetArrayFromJsToNative
{
    
}

-(XCTestExpectation*) createExpectationWithDescription:(nullable NSString*)description
{
    return [self expectationWithDescription:description];
}

-(void) waitForExpectationToFullFillOrTimeOut
{
    [self waitForExpectationsWithTimeout:5.0 handler:^(NSError * _Nullable error) {
        NSLog(@"Test timedout");
    }];
}
@end