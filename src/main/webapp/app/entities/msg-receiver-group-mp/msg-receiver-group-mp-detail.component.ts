import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';

@Component({
  selector: 'jhi-msg-receiver-group-mp-detail',
  templateUrl: './msg-receiver-group-mp-detail.component.html'
})
export class MsgReceiverGroupMpDetailComponent implements OnInit {
  msgReceiverGroup: IMsgReceiverGroupMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ msgReceiverGroup }) => (this.msgReceiverGroup = msgReceiverGroup));
  }

  previousState(): void {
    window.history.back();
  }
}
