import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';

@Component({
  selector: 'jhi-uuc-user-baseinfo-mp-detail',
  templateUrl: './uuc-user-baseinfo-mp-detail.component.html'
})
export class UucUserBaseinfoMpDetailComponent implements OnInit {
  uucUserBaseinfo: IUucUserBaseinfoMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ uucUserBaseinfo }) => (this.uucUserBaseinfo = uucUserBaseinfo));
  }

  previousState(): void {
    window.history.back();
  }
}
