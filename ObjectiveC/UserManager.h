//
//  UserManager.h
//  SecurityDemo
//

#import <Foundation/Foundation.h>

@interface UserManager : NSObject

@property (nonatomic, strong) NSString *apiKey; // Security issue: exposed property
@property (nonatomic, strong) NSMutableArray *users;

- (BOOL)validateEmail:(NSString *)email;
- (BOOL)checkEmailFormat:(NSString *)email; // Duplicate function
- (BOOL)authenticateUser:(NSString *)username password:(NSString *)password;
- (void)logUserAction:(NSString *)action userId:(NSString *)userId;
- (void)debugSensitiveData:(NSDictionary *)data; // Unwanted debug function
- (NSString *)hashPassword:(NSString *)password;
- (void)performUnnecessaryLoop; // Unwanted function

@end