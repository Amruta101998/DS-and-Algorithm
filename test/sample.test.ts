// test/sample.test.ts
import { createArrayOfSize, generateRandomString } from './helpers/testUtils';

describe('Sample Test Suite', () => {
  test('should demonstrate basic test functionality', () => {
    expect(2 + 2).toBe(4);
  });

  test('should work with async/await', async () => {
    const result = await Promise.resolve(42);
    expect(result).toBe(42);
  });

  test('should use test utilities', () => {
    const arr = createArrayOfSize(3);
    expect(arr).toHaveLength(3);
    expect(arr).toEqual([0, 1, 2]);

    const randomStr = generateRandomString(10);
    expect(randomStr).toHaveLength(10);
  });
});