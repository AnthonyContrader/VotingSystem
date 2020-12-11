import { Component, OnInit, OnDestroy } from '@angular/core';
import { Scheda } from '../../core/scheda/scheda.model';
import { SchedaService } from '../../core/scheda/scheda.service';
import { ITEMS_PER_PAGE } from '../../shared/constants/pagination.constants';
import { HttpResponse, HttpHeaders } from '@angular/common/http';
import { JhiEventManager } from 'ng-jhipster';
import { Subscription, combineLatest } from 'rxjs';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';



@Component({
  selector: 'jhi-schede',
  templateUrl: './schede.component.html',
  styleUrls: ['./schede.component.scss']
})
export class SchedeComponent implements OnInit {

  

  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  schedeList: Scheda[] | null = null;
  schedaListSubscription?: Subscription;

  constructor(
    private service: SchedaService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private eventManager: JhiEventManager,

  ) { }

  ngOnInit(): void {
    this.schedaListSubscription = this.eventManager.subscribe('userListModification', () => this.getList());
    this.handleNavigation();
    
  }



  getList(): void{
    this.service.query(
      {
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      }
    ).subscribe((res: HttpResponse<Scheda[]>) => this.onSuccess(res.body, res.headers));
  }

  private sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  private onSuccess(schede: Scheda[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.schedeList = schede;
  }


  private handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      this.page = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      this.predicate = sort[0];
      this.ascending = sort[1] === 'asc';
      this.getList();
    }).subscribe();
  }

  transition(): void {
    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute.parent,
      queryParams: {
        page: this.page,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
      },
    });
  }

  deleteScheda(id: string): void{
    this.service.delete(id).subscribe(
      () => {
        this.getList();
      }
      ,undefined,undefined);
  }


}
