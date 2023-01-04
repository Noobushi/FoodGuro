import { Injectable } from "@angular/core";

@Injectable()
export class TransferService {
    myString: String = "";
    constructor() {

    }
    saveString(text: any) {
        this.myString = text;
    }
    retrieveString() {
        return this.myString;
    }
}
