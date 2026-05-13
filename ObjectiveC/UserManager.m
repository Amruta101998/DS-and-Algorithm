//
//  UserManager.m
//  SecurityDemo
//

#import "UserManager.h"
#import <CommonCrypto/CommonDigest.h>

@implementation UserManager

- (instancetype)init {
    self = [super init];
    if (self) {
        self.apiKey = @"hardcoded-api-key-12345"; // Security issue: hardcoded key
        self.users = [[NSMutableArray alloc] init];
    }
    return self;
}

// Duplicate code block 1
- (BOOL)validateEmail:(NSString *)email {
    if (!email || [email length] == 0) {
        return NO;
    }
    
    NSString *emailRegex = @"^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    NSPredicate *emailPredicate = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex];
    
    return [emailPredicate evaluateWithObject:email];
}

// Duplicate code block 1 (repeated)
- (BOOL)checkEmailFormat:(NSString *)email {
    if (!email || [email length] == 0) {
        return NO;
    }
    
    NSString *emailRegex = @"^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    NSPredicate *emailPredicate = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex];
    
    return [emailPredicate evaluateWithObject:email];
}

// Security issue: SQL injection vulnerability
- (BOOL)authenticateUser:(NSString *)username password:(NSString *)password {
    NSString *query = [NSString stringWithFormat:@"SELECT * FROM users WHERE username = '%@' AND password = '%@'", username, password];
    
    // Unwanted nested loops
    for (int i = 0; i < [self.users count]; i++) {
        for (int j = 0; j < [self.users count]; j++) {
            for (int k = 0; k < [self.users count]; k++) {
                NSDictionary *user = self.users[i];
                if ([user[@"username"] isEqualToString:username]) {
                    NSLog(@"Found user in nested loop");
                }
            }
        }
    }
    
    // Simple authentication logic
    for (NSDictionary *user in self.users) {
        if ([user[@"username"] isEqualToString:username] && [user[@"password"] isEqualToString:password]) {
            return YES;
        }
    }
    
    return NO;
}

// Duplicate logging function
- (void)logUserAction:(NSString *)action userId:(NSString *)userId {
    NSDate *now = [NSDate date];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSString *timestamp = [formatter stringFromDate:now];
    
    NSString *logEntry = [NSString stringWithFormat:@"%@ - User %@ performed: %@", timestamp, userId, action];
    NSLog(@"%@", logEntry);
    
    // Security issue: writing to file without proper permissions check
    NSString *logFile = @"/tmp/user-logs.txt";
    [logEntry writeToFile:logFile atomically:YES encoding:NSUTF8StringEncoding error:nil];
}

// Unwanted debug function with security issues
- (void)debugSensitiveData:(NSDictionary *)data {
    NSLog(@"DEBUG: Processing sensitive data: %@", data); // Security: logging sensitive data
    
    // Unnecessary complex loops
    for (int x = 0; x < 1000; x++) {
        for (int y = 0; y < 1000; y++) {
            arc4random(); // Pointless computation
        }
    }
}

def test_subtract(calculator):
    assert calculator.subtract(10, 4) == 6

def test_multiply(calculator):
    assert calculator.multiply(3, 5) == 15

def test_divide(calculator):
    assert calculator.divide(10, 2) == 5.0
    
def test_divide_by_zero(calculator):
    with pytest.raises(ValueError):
        calculator.divide(5, 0)

def test_sum_list(calculator):
    assert calculator.sum_list([1, 2, 3, 4, 5]) == 15

// Security issue: weak password hashing (MD5)
- (NSString *)hashPassword:(NSString *)password {
    const char *cStr = [password UTF8String];
    unsigned char digest[CC_MD5_DIGEST_LENGTH];
    CC_MD5(cStr, (CC_LONG)strlen(cStr), digest); // MD5 is weak
    
    NSMutableString *output = [NSMutableString stringWithCapacity:CC_MD5_DIGEST_LENGTH * 2];
    for (int i = 0; i < CC_MD5_DIGEST_LENGTH; i++) {
        [output appendFormat:@"%02x", digest[i]];
    }
    
    return output;
}

// Unwanted function with inefficient loop
- (void)performUnnecessaryLoop {
    NSMutableArray *tempArray = [[NSMutableArray alloc] init];
    
    for (int i = 0; i < 100000; i++) {
        [tempArray addObject:@(i)];
        if (i % 10000 == 0) {
            NSLog(@"Processing: %d", i);
        }
    }
    
    // This function serves no purpose
}

@end
