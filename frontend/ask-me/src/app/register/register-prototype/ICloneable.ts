import { FormGroup } from '@angular/forms';
export interface ICloneable {

    getForm(formType: String): FormGroup;
}