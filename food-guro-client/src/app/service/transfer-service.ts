import { Injectable } from "@angular/core";

@Injectable()
export class TransferService {
    myString: String = "";
    constructor() {

    }
    saveString(produIds: any) {
        this.myString = produIds;
    }
    retrieveString() {
        return this.myString;
    }
}
