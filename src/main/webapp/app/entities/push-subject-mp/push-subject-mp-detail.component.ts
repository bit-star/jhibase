import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPushSubjectMp } from 'app/shared/model/push-subject-mp.model';

@Component({
  selector: 'jhi-push-subject-mp-detail',
  templateUrl: './push-subject-mp-detail.component.html'
})
export class PushSubjectMpDetailComponent implements OnInit {
  pushSubject: IPushSubjectMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pushSubject }) => (this.pushSubject = pushSubject));
  }

  previousState(): void {
    window.history.back();
  }
}
