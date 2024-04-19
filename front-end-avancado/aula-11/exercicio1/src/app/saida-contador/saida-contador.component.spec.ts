import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaidaContadorComponent } from './saida-contador.component';

describe('SaidaContadorComponent', () => {
  let component: SaidaContadorComponent;
  let fixture: ComponentFixture<SaidaContadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SaidaContadorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SaidaContadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
