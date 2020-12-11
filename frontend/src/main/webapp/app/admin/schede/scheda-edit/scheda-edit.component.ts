import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SchedaService } from '../../../core/scheda/scheda.service';
import { Scheda } from '../../../core/scheda/scheda.model';

@Component({
  selector: 'jhi-scheda-edit',
  templateUrl: './scheda-edit.component.html',
  styleUrls: ['./scheda-edit.component.scss']
})
export class SchedaEditComponent implements OnInit {

  idScheda!: string;
  scheda!: Scheda;

  isSaving = false;

  editForm = this.fb.group({
    id: [],
    titolo: ['', [Validators.minLength(5), Validators.maxLength(250)]],
    domanda: ['', [Validators.minLength(5) ,Validators.maxLength(250)]],
    risposta1: ['', [Validators.minLength(5), Validators.maxLength(100)]],
    risposta2: ['', [Validators.minLength(5), Validators.maxLength(100)]],
    risposta3: ['', [Validators.minLength(5), Validators.maxLength(100)]],    
  });

  constructor(
    private service: SchedaService,
    private activatedroute: ActivatedRoute, 
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.activatedroute.params.subscribe(
      sch => {
        if(sch['id'] !== undefined)
          this.idScheda = sch['id'];    
      },
      undefined,
      undefined
    );

    if ((this.idScheda !== null) && (this.idScheda !== undefined)){
      this.getScheda();      
    } else {
      this.scheda = new Scheda();
    }
   
  }

  getScheda(): void{
    this.service.read(this.idScheda).subscribe(
      scheda => {this.scheda = scheda},
      undefined, 
      () => this.updateForm(this.scheda));
  } 
  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    this.updateScheda(this.scheda);
    if ((this.idScheda !== null) && (this.idScheda !== undefined)) {
      this.service.update(this.scheda).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    } else {
        this.service.create(this.scheda).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    }
  }


  private updateForm(scheda: Scheda): void {
    this.editForm.patchValue({
      id: scheda.id,
      titolo: scheda.titolo,
      domanda: scheda.domanda,
      risposta1: scheda.primarisposta,
      risposta2: scheda.secondarisposta,
      risposta3: scheda.terzarisposta
    });
  }

  private updateScheda(scheda: Scheda): void{
    scheda.titolo = this.editForm.get(['titolo'])!.value;
    scheda.domanda = this.editForm.get(['domanda'])!.value;
    scheda.primarisposta = this.editForm.get(['risposta1'])!.value;
    scheda.secondarisposta = this.editForm.get(['risposta2'])!.value;
    scheda.terzarisposta = this.editForm.get(['risposta3'])!.value;
  }


  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }
}
