import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlesContadorComponent } from './controles-contador.component';

describe('ControlesContadorComponent', () => {
  let component: ControlesContadorComponent;
  let fixture: ComponentFixture<ControlesContadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ControlesContadorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ControlesContadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
