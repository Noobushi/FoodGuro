import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {
  @Input() fieldName: string = "";
  @Input() placeholderText: string = "";

}
