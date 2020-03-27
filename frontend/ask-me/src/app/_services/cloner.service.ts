import {Injectable} from '@angular/core';
import * as clone from 'clone';

@Injectable({providedIn: 'root'})
export class ClonerService {

    // service for cloning objects 
    deepClone<T>(value): T {
        return clone<T>(value);
    }

}