import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register() {
    let mockLinkSuccess = "http://www.mocky.io/v2/5e6d977e2f00006800a032da"
    let mockLinkFailure = "http://www.mocky.io/v2/5e6da5642f00002b00a032ec"
    let mockLinkAlreadyPresent = "http://www.mocky.io/v2/5e6da7262f00004d00a032f7"
    let apiLink = `${environment.apiUrl}/api/login`
    let link = mockLinkAlreadyPresent

    return this.http.get(link, { observe: 'response' })

  }
}
