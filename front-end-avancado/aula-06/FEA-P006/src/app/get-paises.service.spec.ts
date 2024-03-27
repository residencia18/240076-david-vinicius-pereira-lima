import { TestBed } from '@angular/core/testing';

import { GetPaisesService } from './get-paises.service';

describe('GetPaisesService', () => {
  let service: GetPaisesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetPaisesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
