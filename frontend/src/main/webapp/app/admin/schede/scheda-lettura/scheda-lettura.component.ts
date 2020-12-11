import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { SchedaService } from '../../../core/scheda/scheda.service';
import { Scheda } from '../../../core/scheda/scheda.model';

@Component({
  selector: 'jhi-scheda-lettura',
  templateUrl: './scheda-lettura.component.html',
  styleUrls: ['./scheda-lettura.component.scss']
})
export class SchedaLetturaComponent implements OnInit {
  scheda?: Scheda | null = null;
  idScheda?: any;
  constructor(
    private activatedroute: ActivatedRoute,
    private service: SchedaService
  ) { }

  ngOnInit(): void {
    this.activatedroute.params.subscribe(
      sch => {
        this.idScheda = sch['id'];        
      },
      undefined,
      undefined
    );
    this.getScheda();
  }

  getScheda(): void{
    this.service.read(this.idScheda).subscribe(scheda => {this.scheda = scheda}, undefined, undefined);
  } 

  
}
