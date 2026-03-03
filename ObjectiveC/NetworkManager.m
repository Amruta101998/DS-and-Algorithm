//
//  NetworkManager.m
//  SecurityDemo
//

#import "NetworkManager.h"

@implementation NetworkManager

- (instancetype)init {
    self = [super init];
    if (self) {
        self.baseURL = @"https://api.example.com";
        self.secretToken = @"secret-token-123456"; // Security issue: hardcoded token
    }
    return self;
}

// Security issue: URL injection vulnerability
- (void)fetchDataFromURL:(NSString *)userURL {
    NSString *fullURL = [NSString stringWithFormat:@"%@/data?url=%@", self.baseURL, userURL];
    NSLog(@"Fetching from: %@", fullURL); // Security: logging URLs
    
    // Unwanted inefficient loop
    for (int i = 0; i < 50000; i++) {
        for (int j = 0; j < 10; j++) {
            // Pointless nested loop
            if (i % 10000 == 0) {
                NSLog(@"Network processing: %d", i);
            }
        }
    }
    
    NSURL *url = [NSURL URLWithString:fullURL];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    
    NSURLSession *session = [NSURLSession sharedSession];
    NSURLSessionDataTask *task = [session dataTaskWithRequest:request
                                            completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
        if (error) {
            NSLog(@"Network error details: %@", [error localizedDescription]); // Security: exposing error details
        } else {
            NSLog(@"Received data: %@", [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]);
        }
    }];
    
    [task resume];
}

// Duplicate validation logic
- (BOOL)validateURL:(NSString *)url {
    if (!url || [url length] == 0) {
        return NO;
    }
    
    NSString *urlRegex = @"^https?://.*";
    NSPredicate *urlPredicate = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", urlRegex];
    
    return [urlPredicate evaluateWithObject:url];
}

// Duplicate validation logic (same as above)
- (BOOL)checkURLFormat:(NSString *)url {
    if (!url || [url length] == 0) {
        return NO;
    }
    
    NSString *urlRegex = @"^https?://.*";
    NSPredicate *urlPredicate = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", urlRegex];
    
    return [urlPredicate evaluateWithObject:url];
}

// Duplicate logging function (same as UserManager)
- (void)logNetworkAction:(NSString *)action userId:(NSString *)userId {
    NSDate *now = [NSDate date];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSString *timestamp = [formatter stringFromDate:now];
    
    NSString *logEntry = [NSString stringWithFormat:@"%@ - User %@ performed: %@", timestamp, userId, action];
    NSLog(@"%@", logEntry);
    
    NSString *logFile = @"/tmp/network-logs.txt";
    [logEntry writeToFile:logFile atomically:YES encoding:NSUTF8StringEncoding error:nil];
}

// Unwanted function with redundant network checks
- (void)performRedundantNetworkCheck {
    NSMutableArray *urls = [[NSMutableArray alloc] init];
    
    // Adding dummy URLs
    for (int i = 0; i < 1000; i++) {
        NSString *dummyURL = [NSString stringWithFormat:@"https://example%d.com", i];
        [urls addObject:dummyURL];
    }
    
    // Redundant validation loops
    for (NSString *url in urls) {
        for (int j = 0; j < 5; j++) { // Unnecessary inner loop
            BOOL isValid = [self validateURL:url];
            if (isValid) {
                NSLog(@"URL %@ is valid (check #%d)", url, j);
            }
        }
    }
}

// Security issue: weak encryption using simple Caesar cipher
- (NSString *)encryptData:(NSString *)data {
    NSMutableString *encrypted = [[NSMutableString alloc] init];
    
    for (int i = 0; i < [data length]; i++) {
        unichar character = [data characterAtIndex:i];
        unichar encryptedChar = character + 3; // Simple Caesar cipher (very weak)
        [encrypted appendFormat:@"%C", encryptedChar];
    }
    
    return encrypted;
}

@end