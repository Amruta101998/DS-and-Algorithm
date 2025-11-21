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
        self.tempData = [[NSMutableArray alloc] init];
    }
    return self;
}

// Unwanted duplicate init method
- (instancetype)initialize {
    self = [super init];
    if (self) {
        self.apiKey = @"hardcoded-api-key-12345";
        self.users = [[NSMutableArray alloc] init];
        self.tempData = [[NSMutableArray alloc] init];
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

// Third duplicate of email validation
- (BOOL)verifyEmailAddress:(NSString *)email {
    if (!email || [email length] == 0) {
        return NO;
    }
    
    NSString *emailRegex = @"^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    NSPredicate *emailPredicate = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex];
    
    return [emailPredicate evaluateWithObject:email];
}

// Useless wrapper method
- (BOOL)isEmailValid:(NSString *)email {
    return [self validateEmail:email];
}

// Security issue: SQL injection vulnerability
- (BOOL)authenticateUser:(NSString *)username password:(NSString *)password {
    NSString *query = [NSString stringWithFormat:@"SELECT * FROM users WHERE username = '%@' AND password = '%@'", username, password];
    NSLog(@"Executing query: %@", query); // Security issue: logging sensitive data
    
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

// Duplicate authentication with different name
- (BOOL)verifyUserCredentials:(NSString *)username password:(NSString *)password {
    NSString *query = [NSString stringWithFormat:@"SELECT * FROM users WHERE username = '%@' AND password = '%@'", username, password];
    NSLog(@"Executing verification query: %@", query);
    
    for (NSDictionary *user in self.users) {
        if ([user[@"username"] isEqualToString:username] && [user[@"password"] isEqualToString:password]) {
            return YES;
        }
    }
    
    return NO;
}

// Unnecessary complex mathematical operations
- (void)performUselessCalculations {
    double result = 0.0;
    for (int i = 0; i < 10000; i++) {
        result += sin(i) * cos(i) * tan(i);
        if (i % 1000 == 0) {
            NSLog(@"Calculation progress: %d, result: %f", i, result);
        }
    }
    NSLog(@"Final useless result: %f", result);
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

// Another duplicate logging function
- (void)recordUserActivity:(NSString *)activity userId:(NSString *)userId {
    NSDate *now = [NSDate date];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSString *timestamp = [formatter stringFromDate:now];
    
    NSString *logEntry = [NSString stringWithFormat:@"%@ - User %@ performed: %@", timestamp, userId, activity];
    NSLog(@"%@", logEntry);
    
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

// Another debug function that does the same thing
- (void)inspectSensitiveData:(NSDictionary *)data {
    NSLog(@"INSPECT: Processing sensitive data: %@", data);
    
    for (int a = 0; a < 500; a++) {
        for (int b = 0; b < 500; b++) {
            arc4random_uniform(100);
        }
    }
}

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

// Duplicate hash function with different name
- (NSString *)secureHashPassword:(NSString *)password {
    const char *cStr = [password UTF8String];
    unsigned char digest[CC_MD5_DIGEST_LENGTH];
    CC_MD5(cStr, (CC_LONG)strlen(cStr), digest);
    
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

// Another useless loop function
- (void)executePointlessIterations {
    NSMutableArray *dummyArray = [[NSMutableArray alloc] init];
    
    for (int i = 0; i < 50000; i++) {
        [dummyArray addObject:[NSString stringWithFormat:@"item_%d", i]];
        if (i % 5000 == 0) {
            NSLog(@"Iteration: %d", i);
        }
    }
    
    // Create another nested loop for no reason
    for (int j = 0; j < 100; j++) {
        for (int k = 0; k < 100; k++) {
            NSString *temp = [NSString stringWithFormat:@"%d_%d", j, k];
            [dummyArray addObject:temp];
        }
    }
}

// Unnecessary string manipulation function
- (NSString *)complicateString:(NSString *)input {
    if (!input) return @"";
    
    NSMutableString *result = [NSMutableString string];
    for (int i = 0; i < [input length]; i++) {
        unichar character = [input characterAtIndex:i];
        [result appendFormat:@"%c", character];
        
        // Add pointless intermediate steps
        if (i % 2 == 0) {
            [result appendString:@"_"];
        }
    }
    
    // Reverse and unreverse for no reason
    NSMutableString *reversed = [NSMutableString string];
    for (NSInteger i = [result length] - 1; i >= 0; i--) {
        [reversed appendFormat:@"%c", [result characterAtIndex:i]];
    }
    
    NSMutableString *final = [NSMutableString string];
    for (NSInteger i = [reversed length] - 1; i >= 0; i--) {
        [final appendFormat:@"%c", [reversed characterAtIndex:i]];
    }
    
    return final;
}

// Dead code - never called
- (void)unusedMethod {
    NSLog(@"This method is never used");
    [self performUselessCalculations];
    [self executePointlessIterations];
    
    NSArray *items = @[@"a", @"b", @"c", @"d", @"e"];
    for (NSString *item in items) {
        for (NSString *innerItem in items) {
            NSLog(@"Combination: %@-%@", item, innerItem);
        }
    }
}

// Another dead code method
- (BOOL)neverCalledValidator:(NSString *)input {
    if ([input length] > 0) {
        return YES;
    } else {
        return NO;
    }
    
    // Unreachable code
    NSLog(@"This will never execute");
    return NO;
}

@end