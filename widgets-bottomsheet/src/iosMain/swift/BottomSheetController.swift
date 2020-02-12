/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import UIKit
import FloatingPanel

@objc public class BottomSheetController: NSObject, FloatingPanelControllerDelegate {
  
  private var floatLoayout: FloatingPanelLayout?
  
  @objc public func show(onViewController vc: UIViewController, withContent view: UIView) {

    view.updateConstraints()
    view.layoutSubviews()
  
    let maxSize = CGSize(width: UIScreen.main.bounds.width, height: UIView.layoutFittingCompressedSize.height)
    view.frame = UIScreen.main.bounds

    
    floatLoayout = BottomSheetLayout(preferredHeight:    view.systemLayoutSizeFitting(UIView.layoutFittingCompressedSize, withHorizontalFittingPriority: UILayoutPriority.required, verticalFittingPriority: .defaultLow).height)
    
    let contentVC = UIViewController()
    contentVC.view = view
    let fpc = FloatingPanelController()
    fpc.set(contentViewController: contentVC)
    fpc.delegate = self
    fpc.backdropView.backgroundColor = UIColor(white: 0.0, alpha: 1.0)
    fpc.isRemovalInteractionEnabled = true
    vc.present(fpc, animated: true, completion: nil)
  }
  
  public func floatingPanel(_ vc: FloatingPanelController, layoutFor newCollection: UITraitCollection) -> FloatingPanelLayout? {
    return floatLoayout
  }
  
  public func floatingPanelDidEndRemove(_ vc: FloatingPanelController) {
    print("dismissed?")
  }
}

class BottomSheetLayout: FloatingPanelLayout {
  var initialPosition: FloatingPanelPosition = .half
  private var preferredHeight: CGFloat
  init(preferredHeight: CGFloat) {
    self.preferredHeight = preferredHeight
  }
  
  func insetFor(position: FloatingPanelPosition) -> CGFloat? {
    switch (position) {
    case .half: return preferredHeight
    case .full: return 0
    case .tip: return 0
    case .hidden: return nil
    }
  }
  
  var supportedPositions: Set<FloatingPanelPosition> = [.half, .hidden, .tip]
  
  func backdropAlphaFor(position: FloatingPanelPosition) -> CGFloat {
    return 0.3
  }
}
