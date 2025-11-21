// test/helpers/testUtils.ts
export const createMockObject = <T extends object>(override: Partial<T> = {}): T => {
  return {
    ...override
  } as T;
};

export const wait = (ms: number): Promise<void> => 
  new Promise(resolve => setTimeout(resolve, ms));

export const generateRandomString = (length: number): string => {
  return Array(length)
    .fill(null)
    .map(() => Math.random().toString(36).charAt(2))
    .join('');
};

export const createArrayOfSize = (size: number): number[] => {
  return Array.from({ length: size }, (_, index) => index);
};