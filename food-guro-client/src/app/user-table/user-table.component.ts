import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotifierService } from 'angular-notifier';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent {

  @Input() users!: User[];
  @Input() filterUsers!: User[];
   
  constructor(private userService: UserService, private notifierService: NotifierService, private modalService: NgbModal, private router: Router, private activatedRoute: ActivatedRoute) {
      
  }

  private reloadCurrentRoute() {
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/adminMenu']);
    });
  }

  deleteUser(username: string) {
    this.userService.delete(username).subscribe(x => {
      this.notifierService.notify("success", `User ${x.username} deleted successfully!`);
      this.modalService.dismissAll();
      this.reloadCurrentRoute();
    });
  
  }

  openDelete(form: any, username: string) {
    const modalRef = this.modalService.open(form);
    modalRef.componentInstance.name = username;
  }

  openEdit(form: any, user: User) {
    const modalRef = this.modalService.open(form);
    modalRef.componentInstance.user = user;

  }

  editUser(userForm: User, id: number) {
    const user: User = {
      id: id,
      username: userForm.username,
      firstName: userForm.firstName,
      lastName: userForm.lastName,
      city: userForm.city,
      password: userForm.password,
      userRole: userForm.userRole
    };
    this.userService.edit(user).subscribe(x => {
      this.notifierService.notify("success", `User ${x.username} updated successfully!`);
      this.modalService.dismissAll();
      this.reloadCurrentRoute();
    });
  }
searchByName(name:string) {
  let cloneUsers: User[] = []; 
  this.users.forEach(val => cloneUsers.push(Object.assign({}, val)));  ;

  this.filterUsers = (name) ? cloneUsers.filter(x1 => x1.username.toLocaleLowerCase().includes(name.toLowerCase()))
  : this.users;
}

}
