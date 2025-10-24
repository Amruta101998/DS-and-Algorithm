//
//  NetworkManager.h
//  SecurityDemo
//

#import <Foundation/Foundation.h>

@interface NetworkManager : NSObject

@property (nonatomic, strong) NSString *baseURL;
@property (nonatomic, strong) NSString *secretToken; // Security issue: exposed property

- (void)fetchDataFromURL:(NSString *)userURL; // Security issue: no URL validation
- (BOOL)validateURL:(NSString *)url;
- (BOOL)checkURLFormat:(NSString *)url; // Duplicate function
- (void)logNetworkAction:(NSString *)action userId:(NSString *)userId; // Duplicate logging
- (void)performRedundantNetworkCheck; // Unwanted function
- (NSString *)encryptData:(NSString *)data; // Weak encryption

@end