import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryPotatoeComponent } from './category-potatoes.component';

describe('CategoryPotatoeComponent', () => {
  let component: CategoryPotatoeComponent;
  let fixture: ComponentFixture<CategoryPotatoeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoryPotatoeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryPotatoeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
