import { Injectable } from "@angular/core";

import { AiChatModuleData } from "./moduleClasses/aiChatModuleData";

@Injectable({
  providedIn: 'root'
})
export class GlobalData extends AiChatModuleData {
  private _globalState: Map<string, any>;

  constructor() {
    super();
    this._globalState = new Map<string, any>();
  }

  /**
   * Sets a value in the global state
   * @param key The key to store the value under
   * @param value The value to store
   */
  public setGlobalValue(key: string, value: any): void {
    this._globalState.set(key, value);
  }
  /**
   * Gets a value from the global state
   * @param key The key to retrieve the value for
   * @returns The stored value or undefined if not found
   */
public getGlobalValue<T>(key: string): T | undefined {
    return this._globalState.get(key) as T;
  }
  /**
   * Removes a value from the global state
   * @param key The key to remove
   * @returns true if the value was removed, false if it didn't exist
   */
  public removeGlobalValue(key: string): boolean {
    return this._globalState.delete(key);
  }
  /**
   * Clears all values from the global state
   */
  public clearGlobalState(): void {
    this._globalState.clear();
  }
}
