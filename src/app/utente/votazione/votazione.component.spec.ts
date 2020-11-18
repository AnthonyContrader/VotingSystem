import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VotazioneComponent } from './votazione.component';

describe('VotazioneComponent', () => {
  let component: VotazioneComponent;
  let fixture: ComponentFixture<VotazioneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VotazioneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VotazioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
