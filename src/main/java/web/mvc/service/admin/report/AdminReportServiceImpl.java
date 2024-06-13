package web.mvc.service.admin.report;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.mvc.entity.report.Report;
import web.mvc.exception.common.ErrorCode;
import web.mvc.exception.common.GlobalException;
import web.mvc.repository.report.ReportRepository;
import web.mvc.repository.report.ReportTypeRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminReportServiceImpl implements AdminReportService {

    private final ReportRepository reportRepository;
    private final ReportTypeRepository reportTypeRepository;

    @Override
    public List<Report> findAllReportList() {
        List<Report> list = reportRepository.findAll();

        log.info("list", list);

        return list;
    }

    @Override
    public Report selectReportBySeq(Long reportSeq) {
        Report report = reportRepository.findById(reportSeq)
                .orElseThrow(()->new GlobalException(ErrorCode.NOTFOUND_ID));

        log.info("report", report);

        return report;
    }

    @Override
    public String getReportedUrl(Report report) {
        String url = report.getReportUrl();

        log.info("url", url);

        return "url";
    }

    @Override
    public Report updateReportStatus(Report report) {
        Report updatedReport = reportRepository.updateReportStatus(report.getReportStatus(), report.getReportSeq());

        log.info("updatedStatusReport", updatedReport);

        return updatedReport;
    }

    @Override
    public Report updateReportResult(Report report) {
        Report updatedReport = reportRepository.updateReportStatus(report.getReportResult(), report.getReportSeq());

        log.info("updatedResultReport", updatedReport);

        return updatedReport;
    }

}