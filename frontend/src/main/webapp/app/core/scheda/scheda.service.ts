import { Injectable } from '@angular/core';
import { SERVER_API_URL_1 } from '../../app.constants' 
import { IScheda } from './scheda.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { createRequestOption, Pagination } from '../../shared/util/request-util';

@Injectable({
  providedIn: 'root'
})
export class SchedaService {

 

  public resourceUrl = SERVER_API_URL_1 + 'api/schedas';
  constructor(
    private http: HttpClient
  ) { }

  
  query(req?: Pagination): Observable<HttpResponse<IScheda[]>> {
    const options = createRequestOption(req);
    return this.http.get<IScheda[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<{}> {
    return this.http.delete(this.resourceUrl + '/' + id);
  }

  read(id: string): Observable<IScheda>{
    return this.http.get<IScheda>('http://localhost:8080/services/scheda/api/schedas/' + id);
  }

  create(scheda: IScheda): Observable<IScheda> {
    return this.http.post<IScheda>(this.resourceUrl, scheda);
  }

  update(scheda: IScheda): Observable<IScheda> {
    return this.http.put<IScheda>(this.resourceUrl, scheda);
  }
  getAll(page: number, size: number): Observable<IScheda[]> {   
    return this.http.get<IScheda[]>(this.resourceUrl + '?page=' + page + '&size=' + size);
  }

  getSchedeListWithAlsoVoteUser(idUtente: string): Observable<IScheda[]>{
    return this.http.get<IScheda[]>(this.resourceUrl + "/schede-utente/" + idUtente);
  }


}
