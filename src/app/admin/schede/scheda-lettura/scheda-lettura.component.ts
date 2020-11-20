import { Component, OnInit } from '@angular/core';
import { SchedaService } from '../../../../service/scheda.service';
import { schedavotazioneDTO } from '../../../../dto/schedavotazioneDTO';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-scheda-lettura',
  templateUrl: './scheda-lettura.component.html',
  styleUrls: ['./scheda-lettura.component.css']
})
export class SchedaLetturaComponent implements OnInit {

  schedatoupdate: schedavotazioneDTO;
  id_scheda: number;
  constructor(
    private service: SchedaService,
    private activatedroute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.schedatoupdate = new schedavotazioneDTO();
    this.activatedroute.params.subscribe(
      sch => {
        this.id_scheda = sch['id'];        
      },
      undefined,
      null
    );

      this.getScheda();
      
  }
  getScheda(){
    this.service.read(this.id_scheda).subscribe(schedatoupdate => {this.schedatoupdate = schedatoupdate},undefined,null);

  }

  update(sch: schedavotazioneDTO){
    this.service.update(sch).subscribe(schUpd => {sch = schUpd }, undefined, null);
    this.router.navigate(['/admin-dashboard']);
  }

}
