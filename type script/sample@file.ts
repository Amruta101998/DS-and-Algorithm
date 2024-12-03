import { Injectable } from "@angular/core";

import { AiChatModuleData } from "./moduleClasses/aiChatModuleData";

@Injectable({
    providedIn: 'root'
  })
  export class GlobalData extends AiChatModuleData {

    constructor(){
      super();
    }
  }
