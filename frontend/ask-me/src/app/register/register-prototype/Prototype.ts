import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ClonerService } from '../../_services/cloner.service'
import { ICloneable } from "./ICloneable"

export class Prototype implements ICloneable {

    private forms: Map<string, FormGroup>;
    private clonerService: ClonerService

    constructor() {
        this.loadCache()
    }

    loadCache(){
        this.forms = new Map<string, any>();

        // consider this as a costly process to create a form
        // we can avoid the process of creation of forms from scratch
        // simply return the created form whenever is required
        // we can create different types of forms and store it in Map 
        // and return those forms stored in map whenever queried
        let defaultForm = new FormGroup({
            username: new FormControl('', [Validators.required, Validators.minLength(4)]),
            password: new FormControl('', [Validators.required, Validators.minLength(4)]),
            fname: new FormControl(''),
            lname: new FormControl(''),
            userType: new FormControl('regular'),
        });
        this.forms.set("default-form", defaultForm)
        this.clonerService = new ClonerService()
    }
    
    getForm(formType: string): FormGroup {
        let targetForm = this.forms.get(formType)
        return this.clonerService.deepClone(targetForm)
    }
}