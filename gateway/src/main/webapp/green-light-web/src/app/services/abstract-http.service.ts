import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export abstract class AbstractHttpService<DETAILS, SHORT, DTO, ID> {
  protected http: HttpClient;

  protected constructor(http: HttpClient) {
    this.http = http;
  }

  protected abstract baseUrl(): string;

  public findAll(): Observable<SHORT[]> {
    return this.http.get<SHORT[]>(this.baseUrl());
  }

  public findOne(id: ID): Observable<DETAILS> {
    return this.http.get<DETAILS>(`${this.baseUrl()}/${id}`);
  }

  public create(body: DTO): Observable<DETAILS> {
    return this.http.post<DETAILS>(this.baseUrl(), body);
  }

  public update(id: ID, body: DTO): Observable<DETAILS> {
    return this.http.post<DETAILS>(`${this.baseUrl()}/id`, body);
  }

  public delete(id: ID): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl()}/${id}`);
  }
}
