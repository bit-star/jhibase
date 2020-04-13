import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';

@Component({
  selector: 'jhi-process-msg-task-mp-detail',
  templateUrl: './process-msg-task-mp-detail.component.html'
})
export class ProcessMsgTaskMpDetailComponent implements OnInit {
  processMsgTask: IProcessMsgTaskMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ processMsgTask }) => (this.processMsgTask = processMsgTask));
  }

  previousState(): void {
    window.history.back();
  }
}
