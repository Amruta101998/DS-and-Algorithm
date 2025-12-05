import pytest
from calculator import Calculator

@pytest.fixture
def calculator():
    return Calculator()

def test_add(calculator):
    assert calculator.add(3, 4) == 7

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