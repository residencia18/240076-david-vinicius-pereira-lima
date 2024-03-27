import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaisesJsonComponent } from './paises-json.component';

describe('PaisesJsonComponent', () => {
  let component: PaisesJsonComponent;
  let fixture: ComponentFixture<PaisesJsonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PaisesJsonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PaisesJsonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
