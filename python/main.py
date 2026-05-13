#!/usr/bin/env python3

from calculator import Calculator

def main():
    print("Python Project Running!")
    
    calc = Calculator()
    result = calc.add(25, 17)
    
    print(f"25 + 17 = {result}")
    
    numbers = [1, 2, 3, 4, 5]
    print(f"Sum of list: {calc.sum_list(numbers)}")

if __name__ == "__main__":
    main()